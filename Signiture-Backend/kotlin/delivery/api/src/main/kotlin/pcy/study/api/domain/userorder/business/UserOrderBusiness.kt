package pcy.study.api.domain.userorder.business

import pcy.study.api.common.Log
import pcy.study.api.domain.store.converter.StoreConverter
import pcy.study.api.domain.store.service.StoreService
import pcy.study.api.domain.storemenu.converter.StoreMenuConverter
import pcy.study.api.domain.storemenu.service.StoreMenuService
import pcy.study.api.domain.user.model.UserDetails
import pcy.study.api.domain.userorder.controller.model.UserOrderDetailResponse
import pcy.study.api.domain.userorder.controller.model.UserOrderRequest
import pcy.study.api.domain.userorder.controller.model.UserOrderResponse
import pcy.study.api.domain.userorder.converter.UserOrderConverter
import pcy.study.api.domain.userorder.producer.UserOrderProducer
import pcy.study.api.domain.userorder.service.UserOrderService
import pcy.study.api.domain.userordermenu.convertor.UserOrderMenuConverter
import pcy.study.api.domain.userordermenu.service.UserOrderMenuService
import pcy.study.common.annotation.Business
import pcy.study.db.storemenu.StoreMenu
import pcy.study.db.userorder.UserOrder
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus

@Business
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val userOrderConverter: UserOrderConverter,
    private val storeService: StoreService,
    private val storeConverter: StoreConverter,
    private val storeMenuService: StoreMenuService,
    private val storeMenuConverter: StoreMenuConverter,
    private val userOrderMenuService: UserOrderMenuService,
    private val userOrderMenuConverter: UserOrderMenuConverter,
    private val userOrderProducer: UserOrderProducer,
) {

    companion object : Log

    fun userOrder(
        userDetails: UserDetails,
        request: UserOrderRequest
    ): UserOrderResponse {
        val store = storeService.getStoreWithThrow(request.storeId)
        val storeMenus = getStoreMenus(request.storeMenuIds)
        val userOrder = userOrderConverter.toEntity(userDetails, store, storeMenus)
            .let(userOrderService::order)

        storeMenuOrder(storeMenus, userOrder)

        userOrderProducer.sendOrder(userOrder)
        return userOrderConverter.toResponse(userOrder)
    }

    fun current(userDetails: UserDetails): List<UserOrderDetailResponse> {
        val userOrders = userOrderService.current(userDetails.id)
        return userOrders.map { toUserOrderDetailResponse(it) }
    }

    fun history(userDetails: UserDetails): List<UserOrderDetailResponse> {
        val userOrders = userOrderService.history(userDetails.id)
        return userOrders.map { toUserOrderDetailResponse(it) }
    }

    fun read(
        userDetails: UserDetails,
        orderId: Long
    ): UserOrderDetailResponse {
        val userOrder = userOrderService.getUserOrderWithOutStatusWithThrow(orderId, userDetails.id)
        return toUserOrderDetailResponse(userOrder)
    }

    private fun getStoreMenus(storeMenuIds: List<Long>?): List<StoreMenu>? {
        return storeMenuIds?.stream()
            ?.map { storeMenuService.getStoreMenuWithThrow(it) }
            ?.toList()
    }

    private fun storeMenuOrder(
        storeMenus: List<StoreMenu>?,
        userOrder: UserOrder?
    ) {
        storeMenus?.map {
            userOrderMenuConverter.toEntity(userOrder, it)
        }?.forEach {
            userOrderMenuService.order(it)
        }
    }

    private fun toUserOrderDetailResponse(userOrder: UserOrder): UserOrderDetailResponse {
        val storeMenus = userOrder.userOrderMenus
            ?.filter {
                it.status == UserOrderMenuStatus.REGISTERED
            }?.map {
                it.storeMenu
            }?.toList()

        return UserOrderDetailResponse(
            userOrderConverter.toResponse(userOrder),
            storeConverter.toResponse(userOrder.store),
            storeMenuConverter.toResponse(storeMenus)
        )
    }

}
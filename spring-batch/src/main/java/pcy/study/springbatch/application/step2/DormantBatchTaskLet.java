package pcy.study.springbatch.application.step2;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pcy.study.springbatch.batch.TaskLet;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.user.User;
import pcy.study.springbatch.user.UserRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DormantBatchTaskLet implements TaskLet {

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;

    /**
     * 관심사 분리 필요
     *   - 데이터 수집(Reads)
     *   - 데이터 처리(Processes)
     *   - 데이터 저장(Writes)
     */
    @Override
    public void execute() {
        int pageNo = 0;
        LocalDate dormantDate = LocalDate.now().minusDays(365);

        while (true) {
            // 1. 사용자 조회
            PageRequest pageRequest = PageRequest.of(pageNo++, 1, Sort.by("id").ascending());
            Page<User> page = userRepository.findAll(pageRequest);

            if (page.isEmpty()) {
                break;
            }

            User user = page.getContent().getFirst();

            // 2. 휴면 사용자 추출/변환
            boolean isDormant = dormantDate.isAfter(user.getLoginAt().toLocalDate());
            if(!isDormant) {
                continue;
            }

            user.changeDormant();

            // 3. 휴먼 사용자 상태 저장
            userRepository.save(user);

            // 4. 이메일 발송
            emailProvider.send(user.getEmail(), "휴먼 전환 안내 메일입니다.", "내용");
        }
    }
}

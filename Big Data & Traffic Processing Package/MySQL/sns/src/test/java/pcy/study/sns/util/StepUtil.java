package pcy.study.sns.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pcy.study.sns.domain.follow.repository.FollowRepository;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.repository.MemberRepository;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.entity.Timeline;
import pcy.study.sns.domain.post.repository.PostRepository;
import pcy.study.sns.domain.post.repository.TimelineRepository;

import java.util.List;

@Component
public class StepUtil {

    public Member followingFromMember;
    public List<Member> followingToMembers;
    public Member followerToMember;
    public List<Member> followerFromMembers;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TimelineRepository timelineRepository;

    public Member saveMember() {
        var member = MemberFixtureFactory.createMember();
        return memberRepository.save(member);
    }

    public List<Member> saveMembers(int size) {
        return MemberFixtureFactory.createMembers(size).stream()
                .map(memberRepository::save)
                .toList();
    }

    public void following(Member fromMember, List<Member> toMembers) {
        var follows = toMembers.stream()
                .map(toMember -> FollowFixtureFactory.createFollow(fromMember.getId(), toMember.getId()))
                .toList();
        followRepository.saveAll(follows);
    }

    public void saveFollowingMember(int size) {
        followingFromMember = saveMember();
        followingToMembers = saveMembers(size);
        following(followingFromMember, followingToMembers);
    }

    public void follower(List<Member> fromMembers, Member toMember) {
        var follows = fromMembers.stream()
                .map(fromMember -> FollowFixtureFactory.createFollow(fromMember.getId(), toMember.getId()))
                .toList();
        followRepository.saveAll(follows);
    }

    public void saveFollowerMember(int size) {
        followerToMember = saveMember();
        followerFromMembers = saveMembers(size);
        follower(followerFromMembers, followerToMember);
    }

    public List<Post> saveMembersPost(List<Member> toMembers) {
        return toMembers.stream()
                .map(toMember -> savePost(toMember.getId()))
                .toList();
    }

    public Post savePost(Long memberId) {
        Post post = PostFixtureFactory.createPost(memberId);
        return postRepository.save(post);
    }

    public void saveMemberTimelines(List<Post> posts, Member fromMember) {
        posts.forEach(post -> saveTimeline(post, fromMember.getId()));
    }

    public void saveTimeline(Post post, Long memberId) {
        Timeline timeline = PostFixtureFactory.createTimeline(memberId, post.getId());
        timelineRepository.save(timeline);
    }
}

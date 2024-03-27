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

    public Member fromMember;
    public List<Member> toMembers;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TimelineRepository timelineRepository;

    public Member saveFromMember() {
        var member = MemberFixtureFactory.createMember();
        return memberRepository.save(member);
    }

    public List<Member> saveToMembers(int size) {
        return  MemberFixtureFactory.createMembers(size).stream()
                .map(memberRepository::save)
                .toList();
    }

    public void following(List<Member> toMembers, Member fromMember) {
        var follows = toMembers.stream()
                .map(toMember -> FollowFixtureFactory.createFollow(fromMember.getId(), toMember.getId()))
                .toList();
        followRepository.saveAll(follows);
    }

    public void saveFollowingMember(int size) {
        fromMember = saveFromMember();
        toMembers = saveToMembers(size);
        following(toMembers, fromMember);
    }

    public List<Post> saveToMembersPost(List<Member> toMembers) {
        return toMembers.stream()
                .map(toMember -> savePost(toMember.getId()))
                .toList();
    }

    public Post savePost(Long memberId) {
        Post post = PostFixtureFactory.createPost(memberId);
        return postRepository.save(post);
    }

    public void saveFromMemberTimelines(List<Post> posts, Member fromMember) {
        posts.forEach(post -> saveTimeline(post, fromMember.getId()));
    }

    public void saveTimeline(Post post, Long memberId) {
        Timeline timeline = TimelineFixtureFactory.createTimeline(memberId, post.getId());
        timelineRepository.save(timeline);
    }
}

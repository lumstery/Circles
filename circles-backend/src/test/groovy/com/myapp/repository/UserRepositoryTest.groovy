package com.myapp.repository

import com.jarst.domain.Relationship
import com.jarst.domain.User
import com.jarst.dto.PageParams
import com.jarst.dto.RelatedUserDTO
import com.jarst.dto.UserDTO
import com.jarst.repository.RelationshipRepository
import com.jarst.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired

class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    UserRepository userRepository

    @Autowired
    RelationshipRepository relationshipRepository

    def "findFollowings"() {
        given:
        User user = userRepository.save(new User(username: "akira@test.com", password: "secret", name: "akira"))
        User currentUser = userRepository.save(new User(username: "current@test.com", password: "secret", name: "current"))

        User u1 = userRepository.save(new User(username: "test1@test.com", password: "secret", name: "test1"))
        Relationship r1 = relationshipRepository.save(new Relationship(follower: user, followed: u1))
        relationshipRepository.save(new Relationship(follower: currentUser, followed: u1))

        User u2 = userRepository.save(new User(username: "test2@test.com", password: "secret", name: "test2"))
        Relationship r2 = relationshipRepository.save(new Relationship(follower: user, followed: u2))

        when:
        List<RelatedUserDTO> result = userRepository.findFollowings(user, currentUser, new PageParams())

        then:
        result[0].email == "test2@test.com"
        !result[0].userStats.isFollowedByMe()
        result[0].relationshipId == r2.id
        result[1].email == "test1@test.com"
        result[1].userStats.isFollowedByMe()
        result[1].relationshipId == r1.id
    }

    def "findFollowers"() {
        given:
        User user = userRepository.save(new User(username: "akira@test.com", password: "secret", name: "akira"))
        User currentUser = userRepository.save(new User(username: "current@test.com", password: "secret", name: "current"))

        User u1 = userRepository.save(new User(username: "test1@test.com", password: "secret", name: "test1"))
        Relationship r1 = relationshipRepository.save(new Relationship(followed: user, follower: u1))
        relationshipRepository.save(new Relationship(follower: currentUser, followed: u1))

        User u2 = userRepository.save(new User(username: "test2@test.com", password: "secret", name: "test2"))
        Relationship r2 = relationshipRepository.save(new Relationship(followed: user, follower: u2))

        when:
        List<RelatedUserDTO> result = userRepository.findFollowers(user, currentUser, new PageParams())

        then:
        result[0].email == "test2@test.com"
        !result[0].userStats.isFollowedByMe()
        result[0].relationshipId == r2.id
        result[1].email == "test1@test.com"
        result[1].userStats.isFollowedByMe()
        result[1].relationshipId == r1.id
    }

    def "findOne"() {
        given:
        User user = userRepository.save(new User(username: "akira@test.com", password: "secret", name: "akira"))
        User currentUser = userRepository.save(new User(username: "current@test.com", password: "secret", name: "current"))

        when:
        UserDTO result = userRepository.findOne(user.id, currentUser).get();

        then:
        result.id == user.id
        !result.userStats.followedByMe
    }

}

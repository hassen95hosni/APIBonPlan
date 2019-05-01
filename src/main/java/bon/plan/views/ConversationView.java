package bon.plan.views;

import org.springframework.data.jpa.repository.JpaRepository;

import bon.plan.entities.Conversation;

public interface ConversationView extends JpaRepository<Conversation, Long> {

}

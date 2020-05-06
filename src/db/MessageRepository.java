package db;

import domain.Message;

import java.util.List;

public interface MessageRepository {
    void addMessage(Message message);

    List<Message> chatMessagesReceived(String id);

    List<Message> chatMessagesSent(String id);

    List<String> chatMessagesReceivedString(String id);

    List<String> chatMessagesSentString(String id);

    List<String> chatMessagesString(String id, String  recipId);

}

package db;

import domain.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryStub implements MessageRepository {
    private List<Message> messages;

    public MessageRepositoryStub() {
        messages = new ArrayList<>();
    }

    @Override
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public List<Message> chatMessagesReceived(String id) {
        List<Message> result = new ArrayList<>();
        for (Message m : getAllMessages()) {
            if (m.getRecipientId().equalsIgnoreCase(id)) {
                result.add(m);
            }
        }

        return result;
    }

    @Override
    public List<Message> chatMessagesSent(String id) {
        List<Message> result = new ArrayList<>();
        for (Message m : getAllMessages()) {
            if (m.getSenderId().equalsIgnoreCase(id)) {
                result.add(m);
            }
        }

        return result;
    }

    @Override
    public List<String> chatMessagesReceivedString(String id) {
        List<String> result = new ArrayList<>();
        for (Message m : getAllMessages()) {
            if (m.getRecipientId().equalsIgnoreCase(id)) {
                result.add(m.getMessage());
            }
        }

        return result;
    }

    @Override
    public List<String> chatMessagesSentString(String id) {
        List<String> result = new ArrayList<>();
        for (Message m : getAllMessages()) {
            if (m.getSenderId().equalsIgnoreCase(id)) {
                result.add(m.getMessage());
            }
        }

        return result;
    }

    @Override
    public List<String> chatMessagesString(String id, String recipId) {
        List<String> result = new ArrayList<>();
        for (Message m : getAllMessages()) {



                if (m.getRecipientId().equalsIgnoreCase(id) && m.getSenderId().equalsIgnoreCase(recipId)) {
                    result.add(m.getMessage() + "__--0");
                } else if (m.getSenderId().equalsIgnoreCase(id) && m.getRecipientId().equalsIgnoreCase(recipId)) {
                    result.add(m.getMessage() + "__--1");
                }

        }

        return result;
    }

    private List<Message> getAllMessages() {
        return this.messages;
    }
}

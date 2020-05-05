package db;

import domain.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryStub implements MessageRepository {
    private List<Message> messages;

    public MessageRepositoryStub(){
        messages = new ArrayList<>();
    }

    @Override
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public List<Message> chatMessages(String id) {
        List<Message> result = new ArrayList<>();
        for(Message m : getAllMessages()){
            if(m.getRecipientId().equalsIgnoreCase(id) || m.getSenderId().equalsIgnoreCase(id)){
                result.add(m);
            }
        }
        return result;
    }

    private List<Message> getAllMessages(){
        return this.messages;
    }
}

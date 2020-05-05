package domain;

public class Message {
    private String message;
    private Person recipient, sender;

    public  Message(String message, Person recipient, Person sender){
        setMessage(message);
        setRecipient(recipient);
        setSender(sender);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getRecipient() {
        return recipient;
    }

    public Person getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipientId(){
        return getRecipient().getUserId();
    }

    public String getSenderId(){
        return getSender().getUserId();
    }
}

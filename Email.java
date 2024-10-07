package QueueSim;


public class Email {
    //queue counts represents the number of times, the email was pushed to the queue

    private int queueCount;

    public Email() {

        //The time of creation will be the first time it is pushed to the queue

        queueCount = 1;

    }

    //increments the queue counter

    public void IncCount() {

        queueCount++;

    }

    //returns the queue counter

    public int getQCount() {

        return queueCount;

    }
}

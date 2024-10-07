
package QueueSim;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class EmailSimulator {
    //emailQueue is used to receive emails send them out

    private Queue<Email> emailQueue;

    //This is used to keep track of how many times messages are requed before they are sent

    private HashMap<Integer,Integer> relFrequency;

    //These keep a count of messages received, sent(dequed) and remained in queue after processing them

    //in one minute

    int message_received = 0;

    int message_dequed = 0;

    int inqueue = 0;

    //They keep a count of the total messages received and sent throught the run

    int received_total = 0;

    int dequed_total = 0;

    //Used to calculate the average number of reques

    int total_weight = 0;

    int total_value = 0;

    //initialize the queue and HashMap

    public EmailSimulator() {

        emailQueue = new LinkedList<Email>();

        relFrequency = new HashMap<Integer,Integer>();

    }

    //This method processes the email: Receives the email and tries to send it

    public void emailProcess() {

        //sets the timer to 900 seconds (15 minutes). We will have this loop be executed every second

        int time = 900;

        //create a Random bject for random number generation

        Random rand = new Random();

        //do this while time is greater than 0

        while(time > 0) {

            //show the seconds elapsed

            System.out.println("Time: "+(900-time));

            //receive a message

            receiveEmail(rand);

            //attempt delivery

            dequeMessage(rand);

            //we pause the execution of the program for 1 second using the sleep method

            try {

                java.util.concurrent.TimeUnit.SECONDS.sleep(1);

            } catch(InterruptedException ex) {

                ex.printStackTrace();

            }

            //If a minute has elapsed, increment the overall counts, and reset the minute-wise counters

            if(time % 60 == 0) {

                received_total += message_received;

                dequed_total += message_dequed;

                //inqueue is the number of emails that are left in the queue after processing (recieve and send)

                inqueue += emailQueue.size();

                message_dequed = 0;

                message_received = 0;

            }

            //decrements the time

            time--;

        }

        //print the required statistics

        System.out.println("Total number of messages processed: "+dequed_total);

        System.out.println();

        System.out.println("Average arrival Rate: "+((float)received_total/15.0));

        System.out.println("Average number of messages sent per minute: "+((float)dequed_total/15.0));

        System.out.println("Average number of messages in the queue per minute: "+((float)inqueue/15.0));

        //shows the number of reques

        relFrequency.forEach((key,val) -> {

            System.out.format("Number of messages sent in attempt %d : %d\n",key,val);

            if(key > 1) {

                total_value += (key-1) * val;

                total_weight += key;

            }

        });

        //shows the average number of reques

        System.out.println("Average Number of times messages had to be requed: "+((float)total_value/total_weight));

    }

    public void receiveEmail(Random rand) {

        //generate a random number in range [0,1] and receive a message if we get 1

        //50% chance of receiving a message each second

        boolean rec_email = rand.nextInt(2) == 1 ? true : false;

        if(rec_email) {

            emailQueue.add(new Email());

            message_received++;

            inqueue++;

        }

    }

    public void dequeMessage(Random rand) {

        //generate a random number in range [0,3].

        

        int prob_dq = rand.nextInt(4);

        

        if(!emailQueue.isEmpty()) {

            //if we get [1,3] we successfully dequeue

            if(prob_dq != 0 && message_dequed < 30) {

                int qcount = emailQueue.peek().getQCount();

                if(relFrequency.containsKey(qcount)) {

                    int curr_val = relFrequency.get(qcount);

                    relFrequency.put(qcount,curr_val+1);

                    inqueue--;

                }

                else {

                    relFrequency.put(qcount,1);

                }

                emailQueue.remove();

                message_dequed++;

            }

            //If we get 0, we cannot deque (25% chance) in that case we remove from the head and then enqueue it

            //on the queue

            else {

                Email temp = emailQueue.peek();

                temp.IncCount();

                emailQueue.remove();

                emailQueue.add(temp);

            }

        }

    }

}

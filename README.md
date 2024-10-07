# Email-Queue-Simulator
This Java program simulates the queuing, dequeuing, and processing of emails in a timed environment. The program uses a queue to receive and send out emails while tracking statistics such as the number of emails received, dequeued, and requeued. The simulation runs for 15 minutes in real-time, processing emails every second.


# Key Features:
Email Queuing: Emails are received with a 50% chance each second and are placed into a queue.
Dequeue & Retry Logic: Emails are dequeued with a 75% chance, and unsuccessful dequeue attempts result in requeueing.
Statistics Tracking: The program tracks messages received, dequeued, and requeued over time. It also provides average rates of message arrival, dequeue success, and messages left in the queue per minute.
Requeue Frequency Analysis: The program records how many attempts it takes to successfully dequeue emails and calculates the average number of retries needed.

# Program Structure:
Email.java: Defines the Email class, which tracks how many times an email has been requeued.
EmailSimulator.java: Simulates the email queue process, including receiving, processing, and requeueing of emails, and calculates statistical outputs.
Main.java: Runs the email simulation for 15 minutes (900 seconds).

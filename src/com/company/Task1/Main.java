package com.company.Task1;

import java.io.FileWriter;
import java.util.List;
import java.util.Random;

public class Main
{
    static final int COUNT_TASKS = 10000;
    static final int MAX_TASK_TIME = 2;
    static int processID = 0;
    //Create 3 queues
    static MyQueue<MyProcess> myLowPriorityQueue;
    static MyQueue<MyProcess> myMiddlePriorityQueue;
    static MyQueue<MyProcess> myHighPriorityQueue;
    //Create new processor
    static MyProcessor processor;

    ////////////////
    //main function
    ////////////////
    public static void main(String[] args) throws Exception
    {
        //Create 3 queues
        myLowPriorityQueue = new MyQueue();
        myMiddlePriorityQueue = new MyQueue();
        myHighPriorityQueue = new MyQueue();
        //Create new processor
        processor = new MyProcessor();
        //Create output file
        FileWriter file = new FileWriter("D:\\result.csv");
        int currentCycle = 0;                   //Current cycle iteration
        while(true) {                           //endless cycle
            //Save data to the file
            file.write(generateOutputFileString(currentCycle++));
            Thread.sleep(1);                    //Sleep 1 ms
            MyProcess process;                  //Create new process/task
            if( (process = generateProcess()) == null) {
                completeTask();
                continue;
            }
            //Complete all tasks before exit
            if(processID >= COUNT_TASKS)              //close after 10000 tasks
            {
                if(myHighPriorityQueue.isEmpty() && myMiddlePriorityQueue.isEmpty() && myLowPriorityQueue.isEmpty()) {
                    file.close();
                    break;
                }
                else
                    completeTask();
                continue;
            }
            //Add the process to a queue
            switch (process.Priority) {
                case HIGHEST: {
                    myHighPriorityQueue.enqueue(process);
                    System.out.println(process.Name + " added to HIGH priority queue");
                    break;
                }
                case MIDDLE: {
                    myMiddlePriorityQueue.enqueue(process);
                    System.out.println(process.Name + " added to MIDDLE priority queue");
                    break;
                }
                case LOWEST: {
                    myLowPriorityQueue.enqueue(process);
                    System.out.println(process.Name + " added to LOW priority queue");
                    break;
                }
            }
            completeTask();
        }
    }
    ///////////////////
    //Process generator
    ///////////////////
    public static MyProcess generateProcess()
    {
        Random random = new Random(System.currentTimeMillis());
        int time = random.nextInt(MAX_TASK_TIME+1);
        if(time == 0)                   //there's no task
            return null;
        processID ++;
        MyProcess process = new MyProcess();
        //Generate parameters
        process.Name = "PROCESS_"+ processID;
        process.Time = time;
        //Generate priority
        int temp = random.nextInt(3);   //random number 0..2
        switch (temp)
        {
            case 0:
                process.Priority = Priorities.HIGHEST;
                break;
            case 1:
                process.Priority = Priorities.MIDDLE;
                break;
            case 2:
                process.Priority = Priorities.LOWEST;
                break;
        }
        return process;
    }
    ///////////////////////////
    //Execute task on processor
    ///////////////////////////
    public static void completeTask()
    {
        //Checking new task for breaking the current task in a processor
        if(!myHighPriorityQueue.isEmpty()) {
            try {
                //Can we put task in processor
                if (processor.interrupt(myHighPriorityQueue.front())) {
                    myHighPriorityQueue.dequeue();      //remove task from queue
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if(!myMiddlePriorityQueue.isEmpty()) {
            try {
                //Can we put task in processor
                if (processor.interrupt(myMiddlePriorityQueue.front())) {
                    myMiddlePriorityQueue.dequeue();        //remove task from queue
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if(!myLowPriorityQueue.isEmpty()) {
            try {
                //Can we put task in processor
                if (processor.interrupt(myLowPriorityQueue.front())) {
                    myLowPriorityQueue.dequeue();       //remove task from queue
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else
            System.out.println("PROCESSOR IS WAITING NEW TASK");
    }

    //////////////////////////////////////
    //Generate new string to write to file
    //////////////////////////////////////
    public static String generateOutputFileString(int time) throws Exception
    {
        String outputString = "";
        List<MyProcess> list;
        /*Block highQueue*/
        outputString = time + "; ";
        outputString += "HighPriorityQueue; ";
        outputString += myHighPriorityQueue.size() + "; ";
        if(!myHighPriorityQueue.isEmpty()) {
            list = myHighPriorityQueue.getQueueElements();
            //Get all elements from queue
            for (MyProcess current : list)
                outputString += current.Name + "," + current.Time + ";";
        }
        outputString += "\n";

        /*Block middleQueue*/
        outputString += time + "; ";
        outputString += "MiddlePriorityQueue; ";
        outputString += myMiddlePriorityQueue.size() + "; ";
        if(!myMiddlePriorityQueue.isEmpty()) {
            list = myMiddlePriorityQueue.getQueueElements();
            //Get all elements from queue
            for (MyProcess current : list)
                outputString += current.Name + "," + current.Time + ";";
        }
        outputString += "\n";

        /*Block lowQueue*/
        outputString += time + "; ";
        outputString += "LowPriorityQueue; ";
        outputString += myLowPriorityQueue.size() + "; ";
        if(!myLowPriorityQueue.isEmpty()) {
            list = myLowPriorityQueue.getQueueElements();
            //Get all elements from queue
            for (MyProcess current : list)
                outputString += current.Name + "," + current.Time + ";";
        }
        outputString += "\n";

        /*Block stack*/
        outputString += time + "; ";
        outputString += "Stack; ";
        outputString += processor.getStack().count() + "; ";
        if(!processor.getStack().isEmpty()) {
            list = processor.getStack().getStackElements();
            //Get all elements from queue
            for (MyProcess current : list)
                outputString += current.Name + "," + current.Time + ";";
        }
        outputString += "\n";

        /*Block processor*/
        outputString += time + "; ";
        outputString += "Processor; ";
        MyProcess currentTask = processor.getCurrentTask();
        if(currentTask == null)
            outputString += 0 + "; ";
        else
        {
            outputString += 1 + "; ";
            outputString += currentTask.Name +","+ currentTask.Time + ";";
        }
        outputString +="\n";

        return outputString;
    }

}

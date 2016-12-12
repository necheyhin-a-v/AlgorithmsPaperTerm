package com.company.Task1;

/**
 * Created by ALEKSANDR NECHEUKHIN on 11/4/2016.
 */
public class MyProcessor {
    private boolean IsFree;
    private MyProcess ProcessInProgress;    //Current task in progress
    private long StartTime;                 //Start time for current task
    private MyStack<MyProcess> Stack;       //Stack for delayed tasks
    ///////////////
    //Constructor
    //////////////
    public MyProcessor()
    {
        this.IsFree = true;
        this.Stack = new MyStack<>();
    }
    /////////////////////////////
    //Get stack for delayed tasks
    /////////////////////////////
    public MyStack<MyProcess> getStack()
    {
        return this.Stack;
    }
    ///////////////////////////////////////////
    //Put new task in processor
    //return true if task put in the processor
    ///////////////////////////////////////////
    private void putTask(MyProcess task) throws Exception
    {
        System.out.println("The task " + task.Name + " is in progress now.");
        this.ProcessInProgress = task;
        this.StartTime = System.currentTimeMillis();
        this.IsFree = false;
    }
    ///////////////////////////////////////////////
    //Get current task in a processor. If processor
    //free, it returns null
    ///////////////////////////////////////////////
    public MyProcess getCurrentTask() throws Exception
    {
        if(!this.IsFree)   //Check if processor free. If not, try to free
        {
            long endTime = System.currentTimeMillis();
            if( (endTime-this.StartTime) > this.ProcessInProgress.Time)
            {
                this.StartTime = 0;
                this.IsFree = true;         //Free processor
                this.ProcessInProgress = null;
            }
        }
        return ProcessInProgress;
    }

    //////////////////////////////////////////////
    //Interrupt current task
    //Return true if interruption process succeed
    //////////////////////////////////////////////
    public boolean interrupt(MyProcess applicant) throws Exception
    {

        if(this.isFree())
        {
            this.putTask(applicant);
            return true;
        }
        if(Priorities.hasHighestPriority(applicant.Priority, this.ProcessInProgress.Priority))  //swap tasks
        {
            System.out.println("Try to interrupt current task " + this.ProcessInProgress.Name + " with " + applicant.Name);
            this.Stack.push(ProcessInProgress);
            System.out.println("Current process " + this.ProcessInProgress.Name + " interrupted");
            putTask(applicant);
            return true;
        }
        else
        {
            System.out.println("Cannot interrupt current task " + this.ProcessInProgress.Name + " with "+ applicant.Name);
            return false;
        }
    }
    /////////////////////////
    //Check is processor free
    /////////////////////////
    public boolean isFree() throws Exception
    {
        if(!this.IsFree)   //Check was the task in processor and have it finished
        {
            long endTime = System.currentTimeMillis();
            if( (endTime-this.StartTime) > this.ProcessInProgress.Time)
            {
                System.out.println(this.ProcessInProgress.Name + " finished and processor is free.");
                this.StartTime = 0;
                this.IsFree = true;         //Free processor
                this.ProcessInProgress = null;
                if(!this.Stack.isEmpty())   //Try to run task from stack
                {
                    System.out.println("Try to put " + this.Stack.peek().Name + " task from stack ");
                    putTask(this.Stack.pop());
                }
            }
        }
        return this.IsFree;
    }
}

package project;

import java.util.*;
public class TicketBooker
{
   
    static int availableLowerBerths = 50;
    static int availableMiddleBerths = 21;
    static int availableUpperBerths = 21;
    static int availableRacTickets = 18;
    static int availableWaitingList = 10;
    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList =  new LinkedList<>();
    static List<Integer> bookedTicketList =  new ArrayList<>();
    static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,50));
    static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,67,8,9,10,11,12,13,14,15,16,17,18,19,20,21));
    static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,67,8,9,10,11,12,13,14,15,16,17,18,19,20,21));
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,67,8,9,10,11,12,13,14,15,16,17,18));
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,67,8,9,10));
    static Map<Integer, passenger> passengers = new HashMap<>();
    
    
    public void bookTicket(passenger p, int berthInfo,String allotedBerth)
    {
        
        p.number = berthInfo;
        p.alloted = allotedBerth;
        
        passengers.put(p.passengerId,p);
        
        bookedTicketList.add(p.passengerId);        
        System.out.println("--------------------------Booked Successfully");
    }
    public void addToRAC(passenger p,int racInfo,String allotedRAC)
    {
       p.number = racInfo;
       p.alloted = allotedRAC;
        
        passengers.put(p.passengerId,p);
        
        racList.add(p.passengerId);
          
        availableRacTickets--;
        
        racPositions.remove(0);
        System.out.println("--------------------------added to RAC Successfully");
    }
    
    public void addToWaitingList(passenger p,int waitingListInfo,String allotedWL)
    {
        
        p.number = waitingListInfo; 
        p.alloted = allotedWL;
       
        passengers.put(p.passengerId,p);
        
        waitingList.add(p.passengerId);
            
        availableWaitingList--;
        
        waitingListPositions.remove(0);
        System.out.println("-------------------------- added to Waiting List Successfully");
    }
    
    public void cancelTicket(int passengerId)
    {
        
        passenger p = passengers.get(passengerId);
        passengers.remove(passengerId);
        passengers.remove(Integer.valueOf(passengerId));
     
        bookedTicketList.remove(passengerId);
        bookedTicketList.remove(Integer.valueOf(passengerId));

        
        int positionBooked = p.number;
        System.out.println("---------------cancelled Successfully");
        
        if(p.alloted.equals("L")) 
        { 
          availableLowerBerths++;
          lowerBerthsPositions.add(positionBooked);
        }
        else if(p.alloted.equals("M"))
        { 
          availableMiddleBerths++;
          middleBerthsPositions.add(positionBooked);
        }
        else if(p.alloted.equals("U"))
        { 
          availableUpperBerths++;
          upperBerthsPositions.add(positionBooked);
        }

            passenger passengerFromRAC = passengers.get(racList.poll());
            int positionRac = passengerFromRAC.number;
            racPositions.add(positionRac);
            racList.remove(passengerFromRAC.passengerId);
            racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
            availableRacTickets++;

            
            if(waitingList.size()>0)
            {
               
                passenger passengerFromWaitingList = passengers.get(waitingList.poll());
                int positionWL = passengerFromWaitingList.number;
                waitingListPositions.add(positionWL);
                waitingList.remove(passengerFromWaitingList.passengerId);
                waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

                passengerFromWaitingList.number = racPositions.get(0);
                passengerFromWaitingList.alloted = "RAC";
                racPositions.remove(0);
                racList.add(passengerFromWaitingList.passengerId);
                
                availableWaitingList++;
                availableRacTickets--;
            }
            
            Main.bookTicket(passengerFromRAC);
        }
    
    
  
    public void printAvailable()
    {
        System.out.println("Available Lower Berths "  + availableLowerBerths);
        System.out.println("Available Middle Berths "  + availableMiddleBerths);
        System.out.println("Available Upper Berths "  + availableUpperBerths);
        System.out.println("Availabel RACs " + availableRacTickets);
        System.out.println("Available Waiting List " + availableWaitingList);
        System.out.println("--------------------------");
    }
    
    public void printPassengers()
    {
        if(passengers.size() == 0)
        {
            System.out.println("No details of passengers");
            return;
        }
        for(passenger p : passengers.values())
        {
            System.out.println("PASSENGER ID " + p.passengerId );
            System.out.println(" Name " + p.name );
            System.out.println(" Age " + p.age );
            System.out.println(" Status " + p.number + p.alloted);
            System.out.println("--------------------------");
        }
    }
}
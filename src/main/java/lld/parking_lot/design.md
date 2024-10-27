**Title**: Low-Level Design for Parking Lot Management System

**Overview**: This document provides the low-level design for the parking lot management system that
supports the parking of different vehicle types. The system should handle parking spot management,
ticketing, payments, and vehicle entry/exit.

**Functional Requirements**: *List all the core functionalities of the system*

- Allow vehicles to enter and exit the parking lot.
- Issue parking tickets.
- Track available parking spots by type (car, bike, truck).
- Calculate and process parking fees.

**Non-Functional Requirements**: *Specify constraints like scalability, performance, security, etc..*

- The system should support multiple floors.
- It should handle high traffic efficiently.

**Assumptions**: *List any assumptions about the system (e.g., max vehicle capacity)*

- Vehicles will be parked for a maximum of 24 hours (can change in future).
- The system will support up to 1000 vehicles at a time (can change in future).

**Constraints**: *any constraints (e.g., specific technology stacks)*

- Must use Java 17+ as the programming language.

**Sequence Diagram**

- Parking Vehicle: *Vehicle -> EntryGate (request ParkingSpot) -> ParkingService (find ParkingSpot & generate Ticket) ->
  Ticket -> EntryGate (open gate) -> ParkingSpot (park Vehicle)*
- Exiting: *ParkingSpot (remove Vehicle) -> Vehicle -> ExitGate (request exit) -> ParkingService (generate Bill) ->
  PaymentService (accept payment) -> ExitGate (open gate) -> ParkingService (mark ParkingSpot available)*

**System Overview**: The parking lot system consists of the following components:

- *Vehicle*: car, bike, truck.. anything which need to be parked
- *Gate*: EntryGate and ExitGate handle vehicle entry and exit.
- *ParkingLot*: parking lot itself
- *ParkingFloor*: parking lot might contain more than one floor
- *ParkingSpot*: every floor will have many parking spots
- *ParkingService*: Manages parking floors and spots, and handles Parking operations.
- *Ticket*: every parked vehicle will have a assigned ticket with parking details
- *Bill*: bill will contain the amount to be collected for parking
- *PaymentService*: Handles payment transactions.


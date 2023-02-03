classDiagram
direction BT
class Hotel {
  + Hotel() 
  + currentRevenue() double
  + searchRoom(double) List~Room~
  + searchRoom(Double, Integer, String, String) List~Room~
  + removeRoom(int) String
  + updateRoom(int, Double, Integer, String) String
  + showAllRooms() List~Room~
  + addRoom(Room) boolean
}
class HotelUI {
  + HotelUI() 
  - updateRoom() void
  - removeRoom() void
  - searchByArea() void
  - addRoom() void
  - showRooms() void
  - searchByPrice() void
  - reSetText() void
}
class Main {
  + Main() 
  + main(String[]) void
}
class PremiumRoom {
  + PremiumRoom(int, double, int, String, String) 
  + rentalPrice() int
  + toString() String
}
class Room {
  + Room(int, double, int, String, String) 
  - String status
  - String roomType
  - int number
  - double area
  - int floor
  + updateStatus(String) void
  + toString() String
  + rentalPrice() int
   int number
   double area
   String status
   String roomType
   int floor
}
class StandardRoom {
  + StandardRoom(int, double, int, String, String) 
  + toString() String
  + rentalPrice() int
}
class abstractHotel {
  + abstractHotel() 
  ~ searchRoom(double) List~Room~
  ~ searchRoom(Double, Integer, String, String) List~Room~
  ~ removeRoom(int) String
  ~ currentRevenue() double
  ~ updateRoom(int, Double, Integer, String) String
  ~ addRoom(Room) boolean
}

Hotel "1" *--> "rooms *" Room 
Hotel  -->  abstractHotel 
HotelUI  ..>  Hotel : «create»
HotelUI "1" *--> "hotel 1" Hotel 
HotelUI  ..>  PremiumRoom : «create»
HotelUI  ..>  StandardRoom : «create»
Main  ..>  HotelUI : «create»
PremiumRoom  -->  Room 
StandardRoom  -->  Room 

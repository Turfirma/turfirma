package main.model.domain;

/**
 * Created by Максим on 05.12.2015.
 */
public class Room {

    private int id_room;
    private int id_hotel;
    private int room_number;
    private int capacity;

    public Room() {
    }

    public Room(int id_hotel, int room_number, int capacity) {
        this.id_hotel = id_hotel;
        this.room_number = room_number;
        this.capacity = capacity;
    }

    public Room(int room_number, int id_room, int id_hotel, int capacity) {
        this.room_number = room_number;
        this.id_room = id_room;
        this.id_hotel = id_hotel;
        this.capacity = capacity;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id_room=" + id_room +
                ", id_hotel=" + id_hotel +
                ", room_number=" + room_number +
                ", capacity=" + capacity +
                '}';
    }
}

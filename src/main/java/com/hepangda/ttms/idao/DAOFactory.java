package com.hepangda.ttms.idao;

import com.hepangda.ttms.dao.*;

public class DAOFactory {
    private DAOFactory() {}

    public static IEmployeeDAO createEmployeeDAO() {
        return new EmployeeDAO();
    }
    public static IMovieDAO createMovieDAO(){
        return new MovieDAO();
    }
    public static IScheduleDAO createScheduleDAO(){
        return new ScheduleDAO();
    }
    public static IStudioDAO createStudioDAO() {
    return new StudioDAO();
}
    public static IFoodsDAO createFoodDAO() {
        return new FoodsDAO();
    }
    public static ITicketDAO createTicketDAO() {
        return new TicketDAO();
    }
    public static ISeatDAO createSeatDAO() {
        return new SeatDAO();
    }
}




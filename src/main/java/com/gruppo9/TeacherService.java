package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Path("/TeacherService")
public class TeacherService {

    EventDao eventDao = new EventDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    //ADD AN EVENT
    @POST
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public String addEvent() throws SQLException, ClassNotFoundException {
//        to do : FORM
        Event event = new Event( "web", new Date( 2019,3,3 ), "inserisce", "good1", 100 );
        eventDao.addEvent( event );
        return SUCCESS_RESULT;
    }


    //  SHOW ALL EVENT
    @GET
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public List<Event> getEvents() throws SQLException, ClassNotFoundException {
        return eventDao.getAllEvents();
    }

    @GET
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public Event getEvent(@PathParam("idE") String idE) throws SQLException, ClassNotFoundException {
        int idT=100;
        return eventDao.getEvent( idE,idT );
    }

    //    SHOW ALL PARTECIPANTS OF AN EVENT(ONLY IF IS OF THE SPECIFIC TEACHER)
//    THANKS TO THE ID OF THE EVENTS AND THE ID OF THE SPECIFIC TEACHER
    @GET
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    public Set<Student> getPartecipants(@PathParam("idE") String idE) throws SQLException, ClassNotFoundException {
        int idT = 100;
        return eventDao.getPartecipants( idE,idT).keySet();
    }

    //    SHOW ALL PARTECIPANTS OF AN EVENT(ONLY IF IS OF THE SPECIFIC TEACHER)
//    THANKS TO THE ID OF THE EVENTS AND THE ID OF THE SPECIFIC TEACHER
    @GET
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    public Student getPartecipant(@PathParam("idE") String idE) throws SQLException, ClassNotFoundException {
        int idS = 1;
        return new StudentDao().getStudente( idS );
    }

    // THE TEACHER CAN CONFIRM ALL THE PARTECIPANTS OF HIS EVENT
    @PUT
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String confimAllPartecipants(@PathParam("idE") String idE) throws SQLException, ClassNotFoundException {
        int idT = 100;
        eventDao.setAllPatecipants( idE, idT );

        return SUCCESS_RESULT;
    }

    // THE TEACHER CAN CONFIRM ONE OF THE PARTECIPANTS OF HIS EVENT
    @PUT
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String confimPartecipant(@PathParam("idE") String idE) throws SQLException, ClassNotFoundException {
        int idT = 100;
        int idS = 1;
        eventDao.setPatecipant( idE, idT, idS );
        return SUCCESS_RESULT;
    }

      @PUT
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String modifyEvent(@PathParam("idE") String idE) throws SQLException, ClassNotFoundException {
       int idT=100;
       // to do : FORM
        Event e=new Event( "maker", new Date( 24,3,31 ), "modica", "riuscita", 100 );
        eventDao.updateEvent(e);
        return SUCCESS_RESULT;
   }

    //    THE TEACHER CAN DELETE HIS EVENT
    @DELETE
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteEvent(@PathParam("idE") String idE) throws SQLException, ClassNotFoundException {
        int idT = 100;
        eventDao.deleteEvent( idE, idT );
        return SUCCESS_RESULT;
    }


    @OPTIONS
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, POST,</operations>";
    }

    @OPTIONS
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsE() {
        return "<operations>GET, PUT, DELETE</operations>";
    }

    @OPTIONS
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsP() {
        return "<operations>GET, PUT</operations>";
    }

    @OPTIONS
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsSingleP() {
        return "<operations>GET, PUT</operations>";
    }


}
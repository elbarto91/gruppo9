package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/Initialize")
public class Inizialize {

    EventDao eventDao = new EventDao();
    StudentDao studentDao = new StudentDao();
    TeacherDao teacherDao = new TeacherDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";


    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_XML)
    public String createAll() throws SQLException, ClassNotFoundException {
        Student s = new Student( "name", "prename", 1 );
        Student s1 = new Student( "name1", "prename1", 2 );
        studentDao.addStudente( s );
        studentDao.addStudente( s1 );
        Map<Student, Boolean> p = new HashMap<Student, Boolean>();
        p.put( s, false );
        p.put( s1, false );
        Event event = new Event( "maker", new Date( 2019,05,24  ), "tech1", "good1", 100,p );
        Event event1 = new Event( "bigdata", new Date( 2019,05,23 ), "tech2", "good2", 101,p );
        eventDao.addEvent( event );
        eventDao.addEvent( event1 );
        Teacher t = new Teacher( "nom", "cogn", 100 );
        Teacher t1 = new Teacher( "nom1", "cogn1", 101 );
        teacherDao.addDocente( t );
        teacherDao.addDocente( t1 );
        return SUCCESS_RESULT;
    }


    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_XML)
    public void deleteEvent() {

    }

    @OPTIONS
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}
package com.viettel.common.hibernatedemo;

import com.viettel.domain.Student;
import com.viettel.domain.TblClass;
import java.util.Set;
import java.util.TreeSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringConfiguration.class})
public class AppTest {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest() {
    }

    public Session getCurrenSession() {
        return sessionFactory.getCurrentSession();
    }

    @Test
    @Transactional
    public void testApp() {
        
        Student s = new Student();
        s.setName("Abc");
        
        Set<Student> students = new TreeSet<Student>();
        students.add(s);
        
        TblClass tblClass = new TblClass();
        tblClass.setStudents(students);
        
        getCurrenSession().saveOrUpdate(tblClass);
        System.out.println("==================== "+tblClass.getId()+" / "+s.getId());
        
    }
}

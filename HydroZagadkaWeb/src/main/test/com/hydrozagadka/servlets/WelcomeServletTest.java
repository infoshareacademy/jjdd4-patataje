package com.hydrozagadka.servlets;

import org.junit.Test;

import javax.servlet.ServletException;

import static org.junit.Assert.*;

public class WelcomeServletTest {

    @Test
    public void shouldInitSevlet() throws ServletException {
        // given
        WelcomeServlet subject = new WelcomeServlet();

        // when
        subject.init();

        // then
        assertNotNull(subject.getLoad());
    }

    @Test
    public void doPost() {
    }

    @Test
    public void doGet() {
    }
}
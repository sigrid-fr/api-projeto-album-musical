package com.ifma.muzik.controller.eventos;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

public class CriaRecursoEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 1L;
	
	private final HttpServletResponse response;
    private final Integer id;

    public CriaRecursoEvent(Object source, HttpServletResponse response, Integer id) {
        super(source);

        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Integer getId() {
        return id;
    }
}

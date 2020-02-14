package com.ifma.muzik.controller.eventos;

import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RecursoListener implements ApplicationListener<CriaRecursoEvent>{

	@Override
    public void onApplicationEvent(CriaRecursoEvent eventoCriaRecurso) {
       this.adcionaHeaderLocation( eventoCriaRecurso.getResponse(), eventoCriaRecurso.getId() );
    }

    private void adcionaHeaderLocation(HttpServletResponse response, Integer id) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        response.setHeader("Location", uri.toString() );
    }
}


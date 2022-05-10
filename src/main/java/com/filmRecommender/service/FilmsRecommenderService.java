package com.filmRecommender.service;



import java.util.List;

import com.filmRecommender.model.*;
import com.filmRecommender.repository.ActorRepository;
import com.filmRecommender.repository.CompositorRepository;
import com.filmRecommender.repository.DirectorRepository;
import com.filmRecommender.repository.FilmRepository;
import com.filmRecommender.repository.GuionistaRepository;
import com.filmRecommender.repository.PaisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmsRecommenderService {
    
    @Autowired
	FilmRepository filmRepository;
    @Autowired
	DirectorRepository directorRepository;
    @Autowired
	CompositorRepository compositorRepository;
    @Autowired
	ActorRepository actorRepository;
    @Autowired
	GuionistaRepository guionistaRepository;
    @Autowired
	PaisRepository paisRepository;

    public  List<Director> getDirectores(){
        System.out.println(directorRepository.getDirectores());
        return directorRepository.getDirectores();
    }

    public  List<Compositor> getCompositores(){
        System.out.println(compositorRepository.getCompositores());
        return compositorRepository.getCompositores();
    }

    public  List<Actor> getActores(){
        System.out.println(actorRepository.getActores());
        return actorRepository.getActores();
    }

    public  List<Guionista> getGuionistas(){
        System.out.println(guionistaRepository.getGuionistas());
        return guionistaRepository.getGuionistas();
    }

    public  List<Pais> getPaises(){
        System.out.println(paisRepository.getPaises());
        return paisRepository.getPaises();
    }

}

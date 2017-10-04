package com.bbva.arq.devops.ae.mirrorgate.service;

import com.bbva.arq.devops.ae.mirrorgate.model.Build;
import com.bbva.arq.devops.ae.mirrorgate.model.Event;
import com.bbva.arq.devops.ae.mirrorgate.model.EventType;
import com.bbva.arq.devops.ae.mirrorgate.model.Feature;
import com.bbva.arq.devops.ae.mirrorgate.repository.EventRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    private EventRepository eventRepository;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository){

        this.eventRepository = eventRepository;
    }

    @Override
    public void saveBuildEvent(Build build) {
        LOGGER.info("Saving build event with Id :{}", build.getId());

        Event buildEvent = new Event();

        buildEvent.setEventType(EventType.BUILD);
        buildEvent.setEventTypeCollectionId(build.getId());
        buildEvent.setTimestamp(System.currentTimeMillis());

        eventRepository.save(buildEvent);
    }

    @Override
    public void saveFeatureEvent(Feature feature) {
        LOGGER.info("Saving feature event with Id :{}", feature.getId());

        try{
            Event buildEvent = new Event();

            buildEvent.setEventType(EventType.FEATURE);
            buildEvent.setEventTypeCollectionId(feature.getId());
            buildEvent.setTimestamp(System.currentTimeMillis());

            eventRepository.save(buildEvent);
        } catch (Exception e){
            LOGGER.error("Error while saving event", e);
        }
    }

//    @Override
//    public void saveDeletedFeatureEvent(Long id, String collectorId) {
//        LOGGER.info("Saving feature event with Id :{}", id);
//
//        try{
//            Event buildEvent = new Event();
//
//            buildEvent.setEventType(EventType.FEATURE);
//            buildEvent.setEventTypeCollectionId(feature.getId());
//            buildEvent.setTimestamp(System.currentTimeMillis());
//
//            eventRepository.save(buildEvent);
//        } catch (Exception e){
//            LOGGER.error("Error while saving event", e);
//        }
//    }

    @Override
    public Event getLastEvent(){
        return eventRepository.findFirstByOrderByTimestampDesc();
    }

    @Override
    public List<Event> getEventsSinceTimestamp(Long timestamp){
        return eventRepository.findByTimestampGreaterThanOrderByTimestampAsc(timestamp);
    }
}

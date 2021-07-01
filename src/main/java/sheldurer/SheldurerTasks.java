package sheldurer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

    @Slf4j
    @Component
    public class SheldurerTasks {
    @Autowired
    private RecountMinPriceService recountMinPriceService;

    private static final long TEN_MINUTES = 1000 * 60 *10;

    @Scheduled(fixedRate = TEN_MINUTES)
        public void recountMinPrice(){
        log.debug("Recount min price started");
        recountMinPriceService.recount();
        log.debug("Recont min price finished");
    }
}
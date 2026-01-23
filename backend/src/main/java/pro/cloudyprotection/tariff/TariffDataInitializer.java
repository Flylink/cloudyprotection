package pro.cloudyprotection.tariff;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TariffDataInitializer {

    private final TariffRepository tariffRepository;

    public TariffDataInitializer(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @PostConstruct
    public void initTariffs() {
        seedTariff(15, 100);
        seedTariff(30, 200);
        seedTariff(90, 600);
    }

    private void seedTariff(int days, int priceRub) {
        tariffRepository.findByDays(days).orElseGet(() -> {
            Tariff tariff = new Tariff();
            tariff.setDays(days);
            tariff.setPriceRub(priceRub);
            tariff.setEnabled(true);
            return tariffRepository.save(tariff);
        });
    }
}

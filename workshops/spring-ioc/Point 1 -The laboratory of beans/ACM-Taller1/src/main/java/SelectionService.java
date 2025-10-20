package Point1;

@Component
public class SelectionService {
    @Autowired
    SelectionService(@Qualifier("ConfigurationService1") Service1 Service){
        System.out.println("Inyeccion:"+ Service);
   }
}

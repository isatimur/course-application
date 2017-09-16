package xyz.isatimur.course.application;

import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import xyz.isatimur.course.application.config.ApplicationProperties;
import xyz.isatimur.course.application.config.DefaultProfileUtil;
import xyz.isatimur.course.application.domain.Author;
import xyz.isatimur.course.application.domain.Category;
import xyz.isatimur.course.application.domain.ContentType;
import xyz.isatimur.course.application.domain.Course;
import xyz.isatimur.course.application.domain.Material;
import xyz.isatimur.course.application.domain.Status;
import xyz.isatimur.course.application.repository.AuthorRepository;
import xyz.isatimur.course.application.repository.CategoryRepository;
import xyz.isatimur.course.application.repository.ContentTypeRepository;
import xyz.isatimur.course.application.repository.CourseRepository;
import xyz.isatimur.course.application.repository.MaterialRepository;
import xyz.isatimur.course.application.repository.StatusRepository;
import xyz.isatimur.course.application.service.dto.AuthorDTO;
import xyz.isatimur.course.application.service.mapper.AuthorMapper;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;

@ComponentScan
@EnableAutoConfiguration(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class CourseApplicationApp {

    private static final Logger log = LoggerFactory.getLogger(CourseApplicationApp.class);

    private final Environment env;

    @Autowired
    private CourseRepository repository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ContentTypeRepository contentTypeRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public CourseApplicationApp(Environment env) {
        this.env = env;
    }

    /**
     * Initializes CourseApplication.
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="http://www.jhipster.tech/profiles/">http://www.jhipster.tech/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not " +
                "run with both the 'dev' and 'cloud' profiles at the same time.");
        }

        ContentType contentType1 = new ContentType("VIDEO");
        ContentType contentType2 = new ContentType("PRESENTATION");
        contentTypeRepository.save(contentType1);
        contentTypeRepository.save(contentType2);
        List<Material> materialSet = new ArrayList<>();
        Material material = Material.builder()
            .title("new material")
            .description("newMaterial description like detailed one")
            .duration(360000l)
            .contentType(contentType1)
            .cdn("CDN1")
            .companyId(1l)
            .build();
        materialRepository.save(material);
        materialSet.add(material);
        material = Material.builder()
            .title("2 new material")
            .description("2 newMaterial description like detailed one")
            .duration(360000l)
            .contentType(contentType2)
            .cdn("CDN2")
            .companyId(1l)
            .build();
        materialSet.add(material);
        materialRepository.save(material);
        Category category = new Category("Все курсы");
        categoryRepository.save(category);
        Status status = new Status("Не опубликовано");
        statusRepository.save(status);
        Author author = new Author("Радислав Гандапас",
            "Радислав Гандапас — самый известный в России специалист по лидерству. Автор 8 книг и 14 фильмов по лидерству и ораторскому искусству",
            "test.jpg");
        authorRepository.save(author);

        Course course = Course.builder()
            .url("leader-as-key-to-success" + System.currentTimeMillis())
            .title("Лидер как ключ к успеху")
            .shortDescription("лайтовое описание лидерства")
            .description("Тренинг \"Полная Ж. Полноценная жизнь как главный бизнес-проект человека\". 30 Сен. Тренинг \"SelfMadeMan: самоменеджмент и самомотивация\".")
            .category(category)
            .status(status)
            .author(author)
            .materials(materialSet)
            .tags(null)
            .duration(10000l)
            .price(BigDecimal.ZERO)
            .logoUrl("/logo.gif")
            .publishDate(ZonedDateTime.now(ZoneId.systemDefault()))
            .build();
        repository.save(course);

    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(CourseApplicationApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "External: \t{}://{}:{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            env.getProperty("server.port"),
            protocol,
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            env.getActiveProfiles());
    }


}

/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.web.thymeleaf3.mvc;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import sample.web.thymeleaf3.Message;
import sample.web.thymeleaf3.MessageRepository;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.thymeleaf3.control.TippSurveyServ;
import sample.web.thymeleaf3.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob Winch
 * @author Doo-Hwan Kwak
 */
@Controller
@RequestMapping("/")
public class MessageController {

    @Autowired
    private Survey survey;


    public void setTippSurveyServ(TippSurveyServ tippSurveyServ) {
        this.tippSurveyServ = tippSurveyServ;
    }

    @Autowired
    private TippSurveyServ tippSurveyServ;

	private final MessageRepository messageRepository;

	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@GetMapping
	public ModelAndView list() {
        System.out.println(survey);
        Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Message message) {
		return new ModelAndView("messages/view", "message", message);
	}

	@GetMapping(params = "form")
	public ModelAndView createForm(@ModelAttribute Survey survey) {

        Survey surveyt = getSurvey();
		return new ModelAndView("messages/form", "survey", surveyt);

	}

	@PostMapping
	public HttpEntity<byte[]> create(@Valid Survey survey, BindingResult result,
                                     RedirectAttributes redirect) {

        Survey surveyQuestions = getSurvey();

        byte[] documentBody = tippSurveyServ.getReportTipp(survey,surveyQuestions);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition",
                "attachment; filename=" + "TIPP.pdf".replace(" ", "_"));
        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);

	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.messageRepository.deleteMessage(id);
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Message message) {
		return new ModelAndView("messages/form", "message", message);
	}

	private Survey getSurvey(){

        Section sectionVS = new Section();
        sectionVS.setTitle("FILTRO SENSORIAL VISUAL");
        sectionVS.setShorName("VISUAL");
        sectionVS.setSpecificName("VISUAL\nESPECIAL");
        List<Question> questionsVS = new ArrayList<Question>();
        questionsVS.add(new Question("¿TOMAS NOTAS CUANDO ESTÁS ESTUDIANDO, TRABAJANDO O APRENDIENDO ALGO?"));
        questionsVS.add(new Question("¿NECESITAS DE MATERIAL IMPRESO CUANDO ESTUDIAS?"));
        questionsVS.add(new Question("¿APRENDES MEJOR CREANDO MAPAS MENTALES?"));
        questionsVS.add(new Question("¿LLEVAS LA VISTA AL FRENTE CUANDO ESTÁS PENSANDO ALGO?"));
        questionsVS.add(new Question("¿TE GUSTA HACER PRESENTACIONES EN POWER POINT?"));
        sectionVS.setQuestions(questionsVS);

        Section sectionTG = new Section();
        sectionTG.setTitle("DOMINANCIA DE PROCESO COGNITIVO TEÓRICO GLOBAL  (ABSTRACT RANDOM)");
        sectionTG.setJustWord(true);
        sectionTG.setShorName("TEÓRICO GLOBAL SOCIALIZADOR");
        sectionTG.setSpecificName("HOLÍSTICO/\nGLOBAL\nASOCIATIVO");
        List<Question> questionsTG = new ArrayList<Question>();
        questionsTG.add(new Question("¿CUANDO TRABAJAS EN EQUIPO TE DISTINGUES POR SER UN BUEN ELEMENTO?"));
        questionsTG.add(new Question("¿TE RESULTA RELATIVAMENTE FÁCIL COMPRENDER LAS RELACIONES INTERPERSONALES?"));
        questionsTG.add(new Question("¿CONSIDERAS QUE PUEDES SER O ERES UN BUEN COMUNICADOR?"));
        questionsTG.add(new Question("¿TE GUSTA ESTAR ACOMPAÑADO LA MAYORÍA DEL TIEMPO?"));
        questionsTG.add(new Question("¿TIENES MUCHA IMAGINACIÓN?"));
        questionsTG.add(new Question("¿PUEDES RECONOCER LAS NECESIDADES EMOCIONALES DE OTRAS PERSONAS?"));
        questionsTG.add(new Question("¿ERES UN PENSADOR MÁS GLOBAL, ES DECIR, VES PRIMERO EL TODO QUE EL DETALLE?"));
        questionsTG.add(new Question("SERVICIAL",true));
        questionsTG.add(new Question("IMAGINATIVO",true));
        questionsTG.add(new Question("ADAPTABLE",true));
        questionsTG.add(new Question("RELACIONES",true));
        questionsTG.add(new Question("FLEXIBLE",true));
        questionsTG.add(new Question("PÚBLICO",true));
        questionsTG.add(new Question("ASOCIADO",true));
        questionsTG.add(new Question("ESPONTÁNEO",true));
        questionsTG.add(new Question("COMUNICATIVO",true));
        questionsTG.add(new Question("COMPARTIDO",true));
        questionsTG.add(new Question("COOPERATIVO",true));
        questionsTG.add(new Question("SENSIBLE",true));
        sectionTG.setQuestions(questionsTG);

        Section sectionVE = new Section();
        sectionVE.setTitle("INTELIGENCIA VISO-ESPACIAL (HEMISFERIO DERECHO)");
        sectionVE.setShorName("VISUAL ESPACIAL");
        List<Question> questionsVE = new ArrayList<Question>();
        questionsVE.add(new Question("¿SE TE FACILITA LEER MAPAS?"));
        questionsVE.add(new Question("¿TE GUSTA JUGAR AJEDRÉZ, LEGO,  MECANO O CONSTRUÍR ROMPECABEZAS?"));
        questionsVE.add(new Question("¿NORMALMENTE TIENES UN BUEN OJO PARA LOS DISEÑOS , DIBUJOS Y DETALLES?"));
        questionsVE.add(new Question("¿CONSIDERAS TENER UN BUEN SENTIDO DE LA PROPORCIÓN DE LOS ESPACIOS?"));
        questionsVE.add(new Question("¿PREFIERES LOS VIDEOS Y DIAPOSITIVAS MÁS QUE EL MATERIAL IMPRESO?"));
        questionsVE.add(new Question("¿DISFRUTAS LAS ARTES VISUALES?"));
        questionsVE.add(new Question("¿NORMALMENTE RECUERDAS CON FACILIDAD LUGARES, ESCENAS, CARAS O DETALLES?"));
        sectionVE.setQuestions(questionsVE);

        Section sectionCB = new Section();
        sectionCB.setTitle("PERFIL DE CONTACTO BAJO");
        sectionCB.setShorName("CONTACTO BAJO");
        List<Question> questionsCB = new ArrayList<Question>();
        questionsCB.add(new Question("¿ERES INTROVERTIDO, ES DECIR, ENFOCAS LA ENERGÍA A TRAVÉS DE LA REFLEXIÓN Y LA INTROSPECCIÓN?"));
        questionsCB.add(new Question("¿PUEDES LLEGAR A SER MUY ACADÉMICO, ES DECIR EN ALGÚN TEMA QUE TE GUSTE PROFUDIZAS EN EL ESTUDIO?"));
        questionsCB.add(new Question("¿TE CONSIDERAS UN TRABAJADOR/ESTUDIANTE INDEPENDIENTE?"));
        questionsCB.add(new Question("¿EL ESTRÉS QUE HAY EN EL AMBIENTE LOGRA AFECTARTE MUCHO?"));
        questionsCB.add(new Question("¿ERES NORMALMENTE REACTIVO ANTE CUALQUIER ESTÍMULO, TE CUESTA OFRECER RESPUESTAS DELIBERADAS? "));
        questionsCB.add(new Question("¿PREFIERES TENER TIEMPO SUFICIENTE PARA RELACIONAR, COMPRENDER Y PROCESAR LA INFORMACIÓN?"));
        questionsCB.add(new Question("¿EN GENERAL TE CONSIDERAS UNA PERSONA MUY ORGANIZADA, ADEMÁS ORDENADA?"));
        questionsCB.add(new Question("¿TIENDES SIN DARTE CUENTA O CONSCIENTEMENTE A SUBESTIMAR TUS FORTALEZAS?"));
        sectionCB.setQuestions(questionsCB);

        Section section = new Section();
        section.setTitle("LÍMBICO (aspectos emocionales, cómodos, estéticos y funcionales de la experiencia)");
        section.setDetails1("RD= EMOCIONES");
        section.setDetails2("sentimiento, extrov/introv, sensación");
        section.setShorName("LÍMBICO");
        List<Question> questionsPP = new ArrayList<Question>();
        questionsPP.add(new Question("Hago poco ejercicio y mi metabolismo puede ser lento"));
        questionsPP.add(new Question("Soy optimista y positivo, pero también poco determinado."));
        questionsPP.add(new Question("Me cuesta trabajo decidir.decidir."));
        questionsPP.add(new Question("Aprecio las ideas de otras personas, pero  a veces menosprecio mis propias ideas."));
        questionsPP.add(new Question("Puedo convivir con el desorden sin preocuparme mucho."));
        questionsPP.add(new Question("Me encanta dormir."));
        questionsPP.add(new Question("Tiendo a ser friolento."));
        questionsPP.add(new Question("Me gusta estar acompañado, siento que me energetiza la presencia de otras personas."));
        questionsPP.add(new Question("Puedo ser incondicional con mis compañeros o amigos."));
        questionsPP.add(new Question("Me comunico bien con los animales, la naturaleza y  los niños."));
        questionsPP.add(new Question("Puedo ser muy generoso."));
        questionsPP.add(new Question("Me gusta ser amable con la gente."));
        questionsPP.add(new Question("Busco la armonía en los ambientes."));
        questionsPP.add(new Question("Considero ser una persona cálida."));
        questionsPP.add(new Question("El lugar donde vivo es acogedor y me gusta estar en casa."));
        questionsPP.add(new Question("Me gusta dar y recibir masajes."));
        questionsPP.add(new Question("Me siento mejor con ropa holgada."));
        questionsPP.add(new Question("El ejercicio no me gusta mucho."));
        questionsPP.add(new Question("Me cuesta adaptarme  a los cambios."));
        questionsPP.add(new Question("Puedo estar por un largo periodo de tiempo en una misma posición, no necesito moverme."));
        questionsPP.add(new Question("Me gusta el arte."));
        questionsPP.add(new Question("El trabajo social me motiva y soy paciente enseñando a niños."));
        questionsPP.add(new Question("No me comprometo fácilmente, me cuesta hechar a andar algo."));
        section.setQuestions(questionsPP);

        Section section2 = new Section();
        section2.setTitle("LÍMBICO 2 (aspectos emocionales, cómodos, estéticos y funcionales de la experiencia)");
        section2.setDetails1("RD= EMOCIONES");
        section2.setDetails2("sentimiento, extrov/introv, sensación");
        section2.setShorName("LÍMBICO 2");
        List<Question> questionsPP2 = new ArrayList<Question>();
        questionsPP2.add(new Question("Hago poco ejercicio y mi metabolismo puede ser lento"));
        questionsPP2.add(new Question("Soy optimista y positivo, pero también poco determinado."));
        questionsPP2.add(new Question("Me cuesta trabajo decidir.decidir."));
        questionsPP2.add(new Question("Aprecio las ideas de otras personas, pero  a veces menosprecio mis propias ideas."));
        questionsPP2.add(new Question("Puedo convivir con el desorden sin preocuparme mucho."));
        questionsPP2.add(new Question("Me encanta dormir."));
        questionsPP2.add(new Question("Tiendo a ser friolento."));
        questionsPP2.add(new Question("Me gusta estar acompañado, siento que me energetiza la presencia de otras personas."));
        questionsPP2.add(new Question("Puedo ser incondicional con mis compañeros o amigos."));
        questionsPP2.add(new Question("Me comunico bien con los animales, la naturaleza y  los niños."));
        questionsPP2.add(new Question("Puedo ser muy generoso."));
        questionsPP2.add(new Question("Me gusta ser amable con la gente."));
        questionsPP2.add(new Question("Busco la armonía en los ambientes."));
        questionsPP2.add(new Question("Considero ser una persona cálida."));
        questionsPP2.add(new Question("El lugar donde vivo es acogedor y me gusta estar en casa."));
        questionsPP2.add(new Question("Me gusta dar y recibir masajes."));
        questionsPP2.add(new Question("Me siento mejor con ropa holgada."));
        questionsPP2.add(new Question("El ejercicio no me gusta mucho."));
        questionsPP2.add(new Question("Me cuesta adaptarme  a los cambios."));
        questionsPP2.add(new Question("Puedo estar por un largo periodo de tiempo en una misma posición, no necesito moverme."));
        questionsPP2.add(new Question("Me gusta el arte."));
        questionsPP2.add(new Question("El trabajo social me motiva y soy paciente enseñando a niños."));
        questionsPP2.add(new Question("No me comprometo fácilmente, me cuesta hechar a andar algo."));
        section2.setQuestions(questionsPP2);

        Section sectionSt = new Section();
        List<Question> questionsSt = new ArrayList<Question>();
        questionsSt.add(new Question("PPPRUD",true));
        questionsSt.add(new Question("PPPOS",true));
        questionsSt.add(new Question("PPFLEX",true));
        questionsSt.add(new Question("PPRESP",true));
        questionsSt.add(new Question("PPANA",true));
        questionsSt.add(new Question("PPAR",true));
        questionsSt.add(new Question("PPAUC",true));
        questionsSt.add(new Question("PPCA",true));
        questionsSt.add(new Question("PPCOMP",true));
        questionsSt.add(new Question("PPEMP",true));
        questionsSt.add(new Question("PPMAN",true));
        questionsSt.add(new Question("PPCONEX",true));
        questionsSt.add(new Question("PPCONT",true));
        questionsSt.add(new Question("PPCRE",true));
        questionsSt.add(new Question("PPDES",true));
        questionsSt.add(new Question("PPDISC",true));
        questionsSt.add(new Question("PPEMPR",true));
        questionsSt.add(new Question("PPENF",true));
        questionsSt.add(new Question("PPEQ",true));
        questionsSt.add(new Question("PPESTU",true));
        questionsSt.add(new Question("PPINTEL",true));
        questionsSt.add(new Question("PPINCL",true));
        questionsSt.add(new Question("PPINDIV",true));
        questionsSt.add(new Question("PPINI",true));
        questionsSt.add(new Question("PPACUM (INQ)",true));
        questionsSt.add(new Question("PPID",true));
        questionsSt.add(new Question("PPREST",true));
        questionsSt.add(new Question("PPSIGN",true));
        questionsSt.add(new Question("PPESTR",true));
        questionsSt.add(new Question("PPFUT",true));
        questionsSt.add(new Question("PPEXC",true));
        questionsSt.add(new Question("PPORG",true));
        questionsSt.add(new Question("PPCOMU",true));
        questionsSt.add(new Question("PPREL",true));
        sectionSt.setQuestions(questionsSt);

        Section sectionTraits = new Section();
        sectionTraits.setTitle("RASGOS DE SANA AUTOESTIMA");
        List<Question> questionsTraits = new ArrayList<Question>();
        questionsTraits.add(new Question("AMABILIDAD"));
        questionsTraits.add(new Question("GENERISIDAD"));
        questionsTraits.add(new Question("COOPERACIÓN SOCIAL"));
        questionsTraits.add(new Question("ESPÍRITU DE AYUDA MUTUA"));
        questionsTraits.add(new Question("SE SIENTE DIGNO DE SER QUERIDO"));
        questionsTraits.add(new Question("DEMUESTRA AMOR A LOS DEMÁS"));
        questionsTraits.add(new Question("LAS RELACIONES AMOROSAS FLUYEN CON NATURALIDAD"));
        questionsTraits.add(new Question("PROPICIA ACTIVIDADES Y HECHOS QUE FOMENTAN LA PROPIA FELICIDAD"));
        questionsTraits.add(new Question("SE SIENTE MERECEDOR DE LA FELICIDAD"));
        questionsTraits.add(new Question("RECONOCE LOGROS DE LOS DEMÁS, SABE QUE ESO NO EMPOBRECE SU CAPACIDAD"));
        questionsTraits.add(new Question("RESILIENCIA (capacidad de recuperarse fácilmente de las adversidades)"));
        questionsTraits.add(new Question("NO SE SIENTE SUPERIOR A LOS DEMÁS"));
        questionsTraits.add(new Question("SE SIENTE CÓMODO ANTE PERSONAS CON UN ELEVADO NIVEL DE AUTOESTIMA"));
        questionsTraits.add(new Question("NO NECESITA PROBAR LO QUE ES"));
        questionsTraits.add(new Question("NORMALMENTE TIENE CLARIDAD EN LOS OBJETIVOS"));
        questionsTraits.add(new Question("SE SIENTE CONFIADO Y APTO PARA LA VIDA"));
        questionsTraits.add(new Question("SIENTE PLACER DE VIVIR"));
        questionsTraits.add(new Question("EXPRESIÓN ESPONTÁNEA DE AFECTO"));
        questionsTraits.add(new Question("APERTURA PARA CRÍTICA Y RECONOCER ERRORES"));
        questionsTraits.add(new Question("CONGRUENCIA (actúa como piensa)"));
        questionsTraits.add(new Question("MANEJO MENOS ANSIOSO E INSEGURO FRENTE A ADVERSIDADES"));
        questionsTraits.add(new Question("CAPACIDAD DE CONCERVAR EL EQUILIBRIO Y LA DIGNIDAD EN SITUACIONES DE ESTRÉS"));
        questionsTraits.add(new Question("TIENE IDEAS Y CREENCIAS PROPIAS A PESAR DE LAS TRADICIONES"));
        questionsTraits.add(new Question("VALORA LOS PRODUCTOS DE SU MENTE"));
        questionsTraits.add(new Question("NO SUFRE LA OBSTRUCCIÓN DEL MIEDO Y LA DUDA PERMANENTE"));
        questionsTraits.add(new Question("LA FELICIDAD HUMANA, EL BIENESTAR Y EL PROGRESO SON SUS OBJETIVOS"));
        sectionTraits.setQuestions(questionsTraits);

        List<Section> sectionSensitives = new ArrayList<Section>();
        sectionSensitives.add(sectionVS);

        List<Section> sectionCognitive = new ArrayList<Section>();
        sectionCognitive.add(sectionTG);

        List<Section> sectionIntelligence = new ArrayList<Section>();
        sectionIntelligence.add(sectionVE);

        List<Section> sectionContac = new ArrayList<Section>();
        sectionContac.add(sectionCB);

        List<Section> sectionsProfile = new ArrayList<Section>();
        sectionsProfile.add(section);
        sectionsProfile.add(section2);

        List<Section> sectionsStrength = new ArrayList<Section>();
        sectionsStrength.add(sectionSt);

        List<Section> sectionsTraits = new ArrayList<Section>();
        sectionsTraits.add(sectionTraits);

        Survey survey = new Survey();
        survey.setSectionSensitives(sectionSensitives);
        survey.setSectionCognitive(sectionCognitive);
        survey.setSectionIntelligence(sectionIntelligence);
        survey.setSectionContac(sectionContac);
        survey.setSectionsProfile(sectionsProfile);
        survey.setSectionsStrengths(sectionsStrength);
        survey.setSectionsTraits(sectionsTraits);

        return survey;
    }

}

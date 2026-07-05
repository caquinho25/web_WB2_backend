package pe.edu.upc.wellnessbuddy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatbotIAService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    private static final String SYSTEM_PROMPT_BASE =
            "Eres el asistente de bienestar emocional de WellnessBuddy. " +
                    "Tu rol es brindar apoyo emocional breve, empatico y practico a empleados que reportan estres, " +
                    "ansiedad o cansancio laboral. Responde en espanol, en 2-4 oraciones, con tono calido y cercano. " +
                    "No das diagnosticos medicos. Si detectas senales de crisis grave, recomienda buscar ayuda profesional. " +
                    "Usa el contexto de bienestar del usuario que se te proporciona para personalizar tus respuestas, " +
                    "pero no lo repitas literalmente, integralo de forma natural en la conversacion. " +
                    "Mantente siempre dentro del tema de bienestar emocional y laboral. Si el usuario pregunta algo " +
                    "totalmente ajeno a esto (programacion, tareas academicas, cultura general, etc.), no lo respondas: " +
                    "indicale con amabilidad que tu funcion es acompanarlo en su bienestar emocional, y redirige la " +
                    "conversacion preguntandole como se siente o si hay algo relacionado a su trabajo que le preocupe.";

    public String obtenerRespuesta(String mensajeUsuario, List<String[]> historialPrevio, String contextoUsuario) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        List<Map<String, String>> mensajes = new ArrayList<>();

        String systemPromptCompleto = SYSTEM_PROMPT_BASE;
        if (contextoUsuario != null && !contextoUsuario.isBlank()) {
            systemPromptCompleto += "\n\nContexto actual del empleado:\n" + contextoUsuario;
        }

        Map<String, String> systemMsg = new HashMap<>();
        systemMsg.put("role", "system");
        systemMsg.put("content", systemPromptCompleto);
        mensajes.add(systemMsg);

        if (historialPrevio != null) {
            for (String[] par : historialPrevio) {
                Map<String, String> userMsg = new HashMap<>();
                userMsg.put("role", "user");
                userMsg.put("content", par[0]);
                mensajes.add(userMsg);

                Map<String, String> botMsg = new HashMap<>();
                botMsg.put("role", "assistant");
                botMsg.put("content", par[1]);
                mensajes.add(botMsg);
            }
        }

        Map<String, String> nuevoMsg = new HashMap<>();
        nuevoMsg.put("role", "user");
        nuevoMsg.put("content", mensajeUsuario);
        mensajes.add(nuevoMsg);

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("messages", mensajes);
        body.put("temperature", 0.7);
        body.put("max_tokens", 300);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            Map<String, Object> response = restTemplate.postForObject(OPENAI_URL, request, Map.class);

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            Map<String, Object> primeraOpcion = choices.get(0);
            Map<String, String> mensajeRespuesta = (Map<String, String>) primeraOpcion.get("message");

            return mensajeRespuesta.get("content").trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "Lo siento, no puedo responder en este momento. Intenta de nuevo en unos segundos.";
        }
    }
}
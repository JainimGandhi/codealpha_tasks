import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ChatbotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private static final Map<String, List<String>> intentResponses = new HashMap<String, List<String>>();


    public ChatbotGUI() {
        setTitle("AI Chatbot");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input and send button
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputField.addActionListener(e -> sendMessage());
        sendButton.addActionListener(e -> sendMessage());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        initializeIntents();

        setVisible(true);
    }

    private void sendMessage() {
        String userInput = inputField.getText().trim();
        if (userInput.isEmpty()) return;

        chatArea.append("You: " + userInput + "\n");
        inputField.setText("");

        String response = getBotResponse(userInput.toLowerCase());
        chatArea.append("Bot: " + response + "\n");
    }

    private String getBotResponse(String input) {
        for (String key : intentResponses.keySet()) {
            if (input.contains(key)) {
                List<String> responses = intentResponses.get(key);
                return responses.get(new Random().nextInt(responses.size()));
            }
        }
        return "Sorry, I didn't understand that.";
    }

    private void initializeIntents() {
        intentResponses.put("hi", Arrays.asList("Hello!", "Hi there!", "Hey! How can I help?"));
        intentResponses.put("hey", Arrays.asList("Hello!", "Hi there!", "Hey! How can I help?"));
        intentResponses.put("hello", Arrays.asList("Hello!", "Hi there!", "Hey! How can I help?"));
        intentResponses.put("bye", Arrays.asList("Goodbye!", "See you later!", "Have a nice day!"));
        intentResponses.put("thank", Arrays.asList("You're welcome!", "Happy to help!", "Anytime!"));
        intentResponses.put("hours", Arrays.asList("We are open 9am to 5pm, Monday to Friday."));
        intentResponses.put("open", Arrays.asList("We are open 9am to 5pm, Monday to Friday."));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatbotGUI::new);
    }
                  }

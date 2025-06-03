package app.view;

import java.util.ArrayList;
import java.util.Optional;

import app.model.payments.CreditCard;
import app.model.payments.DanaPayment;
import app.model.payments.Payment;
import app.model.payments.PaymentMethod;
import app.model.payments.SteamPayment;
import app.model.users.Customer;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PaymentPopup {
    public static boolean showPaymentDialog(Customer customer, double totalAmount) {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Pembayaran");
        dialog.getDialogPane().setStyle("-fx-background-color: #1b2838;");

        ButtonType payButtonType = new ButtonType("Bayar Sekarang", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(payButtonType, ButtonType.CANCEL);

        // Dropdown metode pembayaran
        Label metodeLabel = new Label("Pilih Metode Pembayaran:");
        metodeLabel.setStyle("-fx-text-fill: white;");
        ComboBox<String> metodeCombo = new ComboBox<>();
        metodeCombo.getItems().addAll("CreditCard", "SteamWallet", "Dana");
        metodeCombo.setValue("CreditCard");

        // Credit card fields
        Label cardLabel = new Label("Nomor Kartu:");
        cardLabel.setStyle("-fx-text-fill: white;");
        TextField cardNumber = new TextField();
        cardNumber.setPromptText("16 digit");
        cardNumber.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-prompt-text-fill: #c7d5e0;");

        Label expiryLabel = new Label("Expired (MM/YY):");
        expiryLabel.setStyle("-fx-text-fill: white;");
        TextField expiryDate = new TextField();
        expiryDate.setPromptText("MM/YY");
        expiryDate.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-prompt-text-fill: #c7d5e0;");

        VBox creditCardFields = new VBox(8, cardLabel, cardNumber, expiryLabel, expiryDate);

        Label totalLabel = new Label("Total: Rp" + String.format("%,.0f", totalAmount));
        totalLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox content = new VBox(12);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #1b2838;");
        content.getChildren().addAll(totalLabel, metodeLabel, metodeCombo, creditCardFields);
        content.setPrefWidth(350);
        dialog.getDialogPane().setContent(content);

        metodeCombo.setOnAction(e -> {
            String selected = metodeCombo.getValue();
            if (selected.equals("CreditCard")) {
                if (!content.getChildren().contains(creditCardFields)) {
                    content.getChildren().add(creditCardFields);
                }
            } else {
                content.getChildren().remove(creditCardFields);
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == payButtonType) {
                PaymentMethod method;
                String selected = metodeCombo.getValue();
                switch (selected) {
                    case "CreditCard":
                        method = new CreditCard("CC001", cardNumber.getText(), expiryDate.getText());
                        break;
                    case "SteamWallet":
                        method = new SteamPayment("SW001");
                        break;
                    case "Dana":
                        method = new DanaPayment("DANA001");
                        break;
                    default:
                        return false;
                }

                Payment payment = new Payment(totalAmount, method, new ArrayList<>(customer.getCart()));
                return payment.process();
            }
            return false;
        });

        Optional<Boolean> result = dialog.showAndWait();
        return result.orElse(false);
    }
}

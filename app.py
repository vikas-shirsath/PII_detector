from flask import Flask, request, jsonify
import re

app = Flask(__name__)

# Placeholder for ML-based PII detection
def detect_pii_ml(text):
    # Example: Use regex for demonstration (replace with actual ML model)
    pii_patterns = {
        "email": r"\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b",
        "phone": r"\b\d{3}[-.\s]?\d{3}[-.\s]?\d{4}\b",
        "ssn": r"\b\d{3}[-.\s]?\d{2}[-.\s]?\d{4}\b"
    }
    detected_pii = {}
    for pii_type, pattern in pii_patterns.items():
        matches = re.findall(pattern, text)
        if matches:
            detected_pii[pii_type] = matches
    return detected_pii

@app.route('/detect-pii', methods=['POST'])
def detect_pii():
    data = request.json
    text = data.get("text", "")
    results = detect_pii_ml(text)
    return jsonify(results)

if __name__ == '__main__':
    app.run(debug=True)
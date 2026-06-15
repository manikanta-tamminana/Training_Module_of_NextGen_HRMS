const API_BASE_URL = "http://localhost:8080/api/certificates";

export const fetchAllRecords = async () => {
const response = await fetch(`${API_BASE_URL}/all`);

if (!response.ok) {
throw new Error("Failed to fetch records");
}

return response.json();
};

export const saveTrainingRecord = async (formData) => {
const payload = new FormData();

Object.keys(formData).forEach((key) => {
payload.append(key, formData[key]);
});

const response = await fetch(`${API_BASE_URL}/save`, {
method: "POST",
body: payload,
});

if (!response.ok) {
throw new Error(await response.text());
}

return response.text();
};

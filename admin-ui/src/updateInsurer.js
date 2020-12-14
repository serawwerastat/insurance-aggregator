import makeRequestToInsurerApi from "./makeRequestToInsurerApi";

const updateInsurer = async (insurer) => {
    const url = `http://localhost:8085/insurer/${insurer.id}`;
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(insurer),
    };
    return makeRequestToInsurerApi(url, requestOptions);
}

export default updateInsurer
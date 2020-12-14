import makeRequestToInsurerApi from "./makeRequestToInsurerApi";

const createInsurer = async (insurer) => {
    const url = `http://localhost:8085/insurer`;
    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(insurer),
    };
    return makeRequestToInsurerApi(url, requestOptions);
}

export default createInsurer
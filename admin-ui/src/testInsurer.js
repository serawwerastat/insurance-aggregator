import makeRequestToInsurerApi from "./makeRequestToInsurerApi";

const testInsurer = async (insurer) => {
    const url = `http://localhost:8085/insurer/test`;
    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(insurer),
    };
    return makeRequestToInsurerApi(url, requestOptions);
}

export default testInsurer
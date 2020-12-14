import makeRequestToInsurerApi from "./makeRequestToInsurerApi";

const deleteInsurer = async (insurer) => {
    const url = `http://localhost:8085/insurer/${insurer.id}`;
    const requestOptions = {
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},
    };
    return makeRequestToInsurerApi(url, requestOptions);
}

export default deleteInsurer
import makeRequestToInsurerApi from "./makeRequestToInsurerApi";

const changeInsurerActiveState = async (insurer) => {
    const url = `http://localhost:8085/insurer/active/${insurer.id}`;
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
    };
    return makeRequestToInsurerApi(url, requestOptions);
}

export default changeInsurerActiveState
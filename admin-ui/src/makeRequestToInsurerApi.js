const makeRequestToInsurerApi = async (url, requestOptions) => {
    return await fetch(url, requestOptions)
        .then(processResponse)
        .then(res => {
            const {statusCode, data} = res;
            console.log("statusCode", statusCode);
            return data;
        }).catch(error => {
            console.error(error);
            return {name: "network error", description: ""};
        });

    function processResponse(response) {
        const statusCode = response.status;
        const data = response.json();
        return Promise.all([statusCode, data]).then(res => ({
            statusCode: res[0],
            data: res[1]
        }));
    }
}

export default makeRequestToInsurerApi;
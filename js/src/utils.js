
export const chunk = (array, length) => {
    const loop = (toChunk, accumulator) => {
        const nextChunk = toChunk.slice(0, length);
        const remaining = toChunk.slice(length);
        accumulator.push(nextChunk);
        return remaining.length ? loop(remaining, accumulator) : accumulator;
    };
    return loop(array, []);
};

export const checkStatusAndGetJSON = (fetchResponse) =>
    fetchResponse.ok
        ? Promise.resolve(fetchResponse).then(response => response.json())
        : Promise.resolve(fetchResponse).then(apiError => apiError.json()).then(error => Promise.reject(error));
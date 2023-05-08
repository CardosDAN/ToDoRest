import config from './config';


// set the `Authorization` header with the JWT token from your login system
const getHeaders = () => {
    const token = localStorage.getItem('accessToken')
    if (!token) {
        return { 'Content-Type': 'application/json' }
    }
    return {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
    }
}

// make a GET request to the backend API with the specified endpoint
export const get = async (endpoint) => {
    const url = `${config.baseURL}/${endpoint}`
    const response = await fetch(url, {
        method: 'GET',
        headers: getHeaders(),
    })
    return response.json()
}

// make a POST request to the backend API with the specified endpoint and payload
// make a POST request to the backend API with the specified endpoint and payload
export const post = async (endpoint, payload) => {
    const url = `${config.baseURL}/${endpoint}`
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: getHeaders(),
            body: JSON.stringify(payload),
        })
        return response.json()
    } catch (error) {
        console.error(error.message);
        throw error;
    }
}


export const put = async (endpoint, payload) => {
    const url = `${config.baseURL}/${endpoint}`
    const response = await fetch(url, {
        method: 'PUT',
        headers: getHeaders(),
        body: JSON.stringify(payload),
    })
    return response.json()
}

export const del = async (endpoint) => {
    const url = `${config.baseURL}/${endpoint}`
    const response = await fetch(url, {
        method: 'DELETE',
        headers: getHeaders(),
    })
    return response.json()
}

import Axios from 'axios'

class PubClient {
    
    
    get(...path) {
        // const fullPath=path.reduce((full,cur)=>{
        //     return full+'/'+cur
        // },'')
        const result = Axios.get(baseUrl + paths(...path))
        return result
    }

    post(params={}, ...path) {
        Axios.post(baseUrl + paths(...path), params)
    }

    put(params,...path) {
        const result = Axios.put(baseUrl + paths(...path), params, {})  
        return result
    }

    delete(params,...path) {
        const result = Axios.delete(baseUrl + paths(...path)+'?deleteId='+params, {})
        return result
    }
}

const baseUrl = 'http://localhost:8080/todolist_WIP'

const paths = (...path) => {
    return path.reduce((full, cur) => {
        return full + '/' + cur
    }, '')
}

export const pubClient = new PubClient()
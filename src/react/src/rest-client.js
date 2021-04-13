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
        console.log('post')
        Axios.post(baseUrl + paths(...path), params)
    }

    put() {

    }

    delete(params,...path) {
        Axios.delete(baseUrl + paths(...path)+'?deleteId='+params)
    }
}

const baseUrl = 'http://localhost:8080/todolist_WIP'

const paths = (...path) => {
    return path.reduce((full, cur) => {
        return full + '/' + cur
    }, '')
}

export const pubClient = new PubClient()
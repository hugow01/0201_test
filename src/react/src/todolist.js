import React, { useEffect, useState } from 'react'
import { pubClient } from './rest-client'
import { v4 as uuid } from 'uuid'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowAltCircleDown, faArrowAltCircleUp, faTimesCircle } from '@fortawesome/free-solid-svg-icons'

export default () => {
    const CONSTRUCT = { id: '', orderBy: 0, priority: 0, title: '' }
    const [content, setContent] = useState([])
    const [tempTitle, setTempTitle] = useState('')
    const [editable, setEditable] = useState('')


    useEffect(() => {
        handleLoad()
    }, [])

    const handleLoad = async () => {
        const { data } = await pubClient.get('todolist')
        setContent([...data])
    }

    function handleEnter() {
        console.log(content)
        if (tempTitle) {
            const temp = { id: uuid(), orderBy: (content.length), priority: 0, title: tempTitle }
            content.push(temp)
            setTempTitle('')
            pubClient.post(temp, 'todolist')
        }

    }

    function handleSave() {
        console.log('save')
        pubClient.post(content, 'todolist')

    }

    function handleFocus(index) {
        setEditable(index)
    }

    function handleChange(index, event) {
        const value = event.target.value
        let tempContent = [...content]
        tempContent[index] = { ...tempContent[index], title: value }
        setContent(tempContent)
    }

    function handleEdit(index) {
        pubClient.put(index)
    }

    function handleDelete(index) {
        let temp = [...content]
        const id = temp[index].id
        temp.splice(index, 1)
        setContent(temp)
        pubClient.delete(id, 'todolist')
    }

    function handleswap(index, swap) {
        const lastIndex = content.length - 1
        if (index !== lastIndex || index !== 0) {
            let tempContent = [...content]
            let temp = { ...tempContent[index + swap], orderBy: index }
            tempContent[index + swap] = { ...tempContent[index], orderBy: index + swap }
            tempContent[index] = temp
            setContent(tempContent)
        }

    }

    return (
        <>
            <div className='no-gutters'>
                <h1 >Todo List</h1>
                <div className='text-start'>
                    <label>輸入代辦事項</label>
                    <div className='d-flex justify-content-between'>
                        <input
                            type='text'
                            name='todoTitle'
                            className='form-control'
                            value={tempTitle}
                            onChange={event => setTempTitle(event.target.value)}
                            onKeyDown={event => { if (event.keyCode === 13) { handleEnter() } }}
                        />
                        <button
                            className='form-control w-25'
                            onClick={handleEnter}
                        >輸入
                        </button>
                    </div>
                </div>
                <hr />
                {/* <div className="card card-task shadow-sm">
                <div className="card-body">
                    <div className="card-title">
                    <a href="#"><h6>Task Name</h6></a>
                    <span className="text-small">Due Tomorrow</span>
                    </div>
                    <div className="card-meta">
                    <div className="d-flex align-items-center">
                        <i className="material-icons">playlist_add_check</i>
                        <span>-/-</span>
                    </div>
                    <div className="dropdown card-options">
                        <button className="btn-options" type="button" id="task-dropdown-button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i className="material-icons">more_vert</i>
                        </button>
                        <div className="dropdown-menu dropdown-menu-right">
                        ...
                        </div>
                    </div>
                    </div>
                </div>
                </div> */}
                <div className='text-start form-group px-3'>
                    {content.map((rowData, index) => (
                        <div className='card shadow-sm mt-2 px-3 d-flex row' key={rowData.id} onDoubleClick={() => { handleFocus(index) }}>
                            <div className="input-group my-1">
                                <div className="input-group-prepend">
                                    <div className="input-group-text p-3">
                                        <input type="checkbox" 
                                            aria-label="Checkbox for following text input" 
                                        />
                                    </div>
                                </div>
                                <input 
                                    type="text" 
                                    className="form-control mr-1 border-0 border-bottom bg-light" 
                                    aria-label="Text input with checkbox" 
                                    value={rowData.title}
                                    disabled={editable !== rowData.orderBy}
                                    onChange={event => handleChange(index, event)}
                                    onBlur={() => setEditable('')}
                                    onKeyDown={event => { if (event.keyCode === 13) { setEditable('') } }}
                                />
                            <div>
                                <button
                                    className='btn px-1'
                                    disabled={rowData.orderBy === 0}
                                    onClick={() => {
                                        handleswap(rowData.orderBy, -1)
                                        console.log('up')
                                    }}
                                >
                                    <FontAwesomeIcon icon={faArrowAltCircleUp} size='lg' />
                                </button>

                                <button
                                    className='btn px-1'
                                    disabled={rowData.orderBy === (content.length - 1)}
                                    onClick={() => {
                                        handleswap(rowData.orderBy, 1)
                                        console.log('down')
                                    }}
                                >
                                    <FontAwesomeIcon icon={faArrowAltCircleDown} size='lg' />
                                </button>
                                <button
                                    className='btn px-1'
                                    onClick={() => { handleDelete(index) }}
                                >
                                    <FontAwesomeIcon icon={faTimesCircle} size='lg' />
                                </button>
                            </div>
                            </div>
                        </div>
                    ))}
                </div>
                            {content.length > 0 &&
                                <>
                                    <hr />
                                    <div className='d-flex justify-content-center'>
                                        <button className='btn btn-danger mx-1' onClick={() => setContent([])}>清除</button>
                                        <button className='btn btn-primary mx-1' onClick={() => console.log(content)}>儲存</button>
                                        {/* <button className='btn btn-primary mx-1' onClick={handleSave}>儲存</button> */}
                                    </div>
                                </>
                            }
                        </div>
        </>
    )

}
import React from 'react';
import  Card  from 'react-bootstrap/Card';
import Button from "react-bootstrap/Button";
import './Card.css';

const TaskCard = ({ task }) => {
    return (
        <Card className="text-center">
            <Card.Header>{task.priority}</Card.Header>
            <Card.Body>
                <Card.Title>{task.title}</Card.Title>
                <Card.Text>
                    {task.description}
                </Card.Text>
                <Button variant="primary">See more</Button>
            </Card.Body>
            <Card.Footer className="text-muted">{task.dueDate}</Card.Footer>
        </Card>
    );
};

export default TaskCard;
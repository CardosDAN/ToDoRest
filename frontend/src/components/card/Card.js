import React from 'react';
import { Card } from 'react-bootstrap';

const TaskCard = ({ task }) => {
    return (
        <Card>
            <Card.Body>
                <Card.Title>{task.title}</Card.Title>
                <Card.Text>{task.description}</Card.Text>
            </Card.Body>
        </Card>
    );
};

export default TaskCard;

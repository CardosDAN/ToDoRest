import React from 'react';
import Card from './Card';
import { Container, Row, Col } from 'react-bootstrap';

const CardList = ({ tasks }) => {
    return (
        <Container>
            <Row xs={1} sm={2} md={3} className="g-4">
                {tasks && tasks.map((task) => (
                    <Col key={task.id}>
                        <Card task={task} />
                    </Col>
                ))}
            </Row>
        </Container>
    );
};

export default CardList;

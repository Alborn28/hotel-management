import React from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Home: React.FC = () => {
    const navigate = useNavigate();

    const navigateToBooking = () => navigate("book");

    return (
        <Container className="pt-5">
            <Row className="mb-3">
                <Col>
                    <h2>Hotellet</h2>
                </Col>
                <Col className="col-auto">
                    <Button variant="primary" onClick={navigateToBooking}>
                        Boka rum
                    </Button>
                </Col>
            </Row>
        </Container>
    );
};

export default Home;

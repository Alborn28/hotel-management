import React, { useState } from "react";
import {
    Alert,
    Button,
    Col,
    Container,
    FloatingLabel,
    Form,
    Row,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import api from "../utils/api";
import { getErrorMessage } from "../utils/errorUtils";

enum RoomSize {
    SINGLE = "SINGLE",
    DOUBLE = "DOUBLE",
}

interface ReservationRequest {
    start: string;
    end: string;
    email: string;
    roomId?: string;
    roomType?: string;
}

const BookingView: React.FC = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [roomType, setRoomSize] = useState<RoomSize>(RoomSize.SINGLE);

    const [errorMessage, setErrorMessage] = useState<string>();

    const navigateToList = () => navigate("/");

    const onSuccess = () => navigateToList();

    const onError = (error: any) =>
        setErrorMessage(getErrorMessage(error?.response?.data?.message ?? ""));

    const handleSubmit = (e: any) => {
        e.preventDefault();
        const payload: ReservationRequest = {
            start: startDate,
            end: endDate,
            email,
            roomType,
        };
        api.post("reservation-v1", payload).then(onSuccess, onError);
    };

    return (
        <Container className="pt-5">
            <Row className="mb-3">
                <h2>Boka rum</h2>
            </Row>

            <Form onSubmit={handleSubmit}>
                <Form.Group as={Row} className="mb-3">
                    <Col>
                        <FloatingLabel label="E-post">
                            <Form.Control
                                type="email"
                                value={email}
                                placeholder="Ange e-post"
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </FloatingLabel>
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3">
                    <Col>
                        <FloatingLabel label="Startdatum">
                            <Form.Control
                                type="date"
                                value={startDate}
                                onChange={(e) => setStartDate(e.target.value)}
                                required
                            />
                        </FloatingLabel>
                    </Col>
                    <Col>
                        <FloatingLabel label="Slutdatum">
                            <Form.Control
                                type="date"
                                value={endDate}
                                onChange={(e) => setEndDate(e.target.value)}
                                required
                            />
                        </FloatingLabel>
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3">
                    <Col>
                        <FloatingLabel label="Typ av rum">
                            <Form.Select
                                value={roomType}
                                onChange={(e) =>
                                    setRoomSize(e.target.value as RoomSize)
                                }
                                required
                            >
                                <option value={RoomSize.SINGLE}>
                                    Enkelrum
                                </option>
                                <option value={RoomSize.DOUBLE}>
                                    Dubbelrum
                                </option>
                            </Form.Select>
                        </FloatingLabel>
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3">
                    <Col>
                        <Button
                            variant="secondary"
                            type="reset"
                            onClick={navigateToList}
                        >
                            Avbryt
                        </Button>
                        <Button variant="primary" type="submit">
                            Boka
                        </Button>
                    </Col>
                </Form.Group>
            </Form>

            <Alert
                variant="danger"
                show={errorMessage !== undefined}
                onClose={() => setErrorMessage(undefined)}
                dismissible
            >
                {errorMessage}
            </Alert>
        </Container>
    );
};

export default BookingView;

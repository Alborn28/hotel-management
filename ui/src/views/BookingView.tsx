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

enum RoomType {
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

interface Reservation {
    id: string;
    start: string;
    end: string;
    email: string;
    room: Room;
}

interface Room {
    id: string;
    number: number;
    type: RoomType;
}

const BookingView: React.FC = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [roomType, setRoomType] = useState<RoomType>(RoomType.SINGLE);

    const [successMessage, setSuccessMessage] = useState<string>();
    const [errorMessage, setErrorMessage] = useState<string>();

    const navigateToList = () => navigate("/");

    const onSuccess = (response: any) => {
        const reservation: Reservation = response.data;
        setErrorMessage(undefined);
        setSuccessMessage(`Rum ${reservation.room.number} har bokats.`);
    };

    const onError = (error: any) => {
        setSuccessMessage(undefined);
        setErrorMessage(getErrorMessage(error?.response?.data?.message ?? ""));
    };

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
                                    setRoomType(e.target.value as RoomType)
                                }
                                required
                            >
                                <option value={RoomType.SINGLE}>
                                    Enkelrum
                                </option>
                                <option value={RoomType.DOUBLE}>
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
                            Tillbaka
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
            <Alert
                variant="success"
                show={successMessage !== undefined}
                onClose={() => setSuccessMessage(undefined)}
                dismissible
            >
                {successMessage}
            </Alert>
        </Container>
    );
};

export default BookingView;

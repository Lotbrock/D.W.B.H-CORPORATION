import './footer.css';

import React from 'react';
import { Translate } from 'react-jhipster';
import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
        <p>
          <Translate contentKey="footer">D.W.B.H</Translate> @CopyRight 2019
        </p>
      </Col>
    </Row>
  </div>
);

export default Footer;
